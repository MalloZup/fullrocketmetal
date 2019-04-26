(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [fullrocketmetal.reminders :as reminders]
            [fullrocketmetal.scheduler :as rock-sched]
            [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.conversion :as qc]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.simple :refer [schedule with-repeat-count with-interval-in-milliseconds]]
            [missile.chat :as chat])
   (:gen-class))

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))

(defn daemonize []
  (while true
    ;; todo watch if a file changed (conf) and perfom some operation
    (let [interval (* 5 60 1000)] 
      (Thread/sleep interval))
    (println "sleeping 5 min")
    ;; print debug infos  ;; TODO
    (flush)))

(defjob rocket-message-job  [ctx]
  (let [data (qc/from-job-data ctx)
     ch-name (data "ch-name")
     text-message (data "text-message")]
  (println ch-name)
  (println text-message)))

(defn create-rocket-msg-job [list-jobs]
(j/build
  (j/of-type rocket-message-job)
  (j/using-job-data {"ch-name" (:channel-name list-jobs) "text-message" (:message list-jobs)})))
         ;; don't use for moment job identity.  A possible one could be "channel-name+test-message+time" concatenate ID and maybe some random
         ;; (j/with-identity (j/key "jobs.noop.1"))))


;; TODO REMOVE THIS LATER
(def jobs-fake 
  [
    {:channel-name "caasp-release-squad" :chron-schedule "16.30" :message "[autogenerated] @here meeting in 5 min! GTM board here: https://app.gotomeeting.com/?meetingId=YOUR_NUMBER"},
    {:channel-name "caasp-feature-squad" :chron-schedule "16.00" :message "i'm just a text after the default message"},
  ])


(defn schedule-job-and-trigger [jobs-map]

    (let [s  (-> (qs/initialize) qs/start)
            job (create-rocket-msg-job jobs-map)
            trigger (rock-sched/create-rocket-msg-cron-trigger)]
        ;; TODO: investigate on this call
        (qs/schedule s job trigger)))

(defn -main []
  (init-rocketchat-client) 
  ;; https://stuartsierra.com/2015/08/10/clojure-donts-redundant-map
  (run! schedule-job-and-trigger jobs-fake)
  (daemonize))
