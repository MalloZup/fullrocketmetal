(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [fullrocketmetal.reminders :as reminders]
            [fullrocketmetal.scheduler :as rock-sched]
            [clojurewerkz.quartzite.scheduler :as qs]
           
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
      (let [interval (* 5 60 1000)] 
      (Thread/sleep interval))))

(def events-reminders
  (reminders/get-reminders))

(defn schedule-job-and-triggers [jobs-map]
    (let [s  (-> (qs/initialize) qs/start)
      job (rock-sched/create-rocket-msg-job jobs-map)
      trigger (rock-sched/create-rocket-msg-cron-trigger (:cron-schedule jobs-map))]
      (qs/schedule s job trigger)))

(defn -main []
  (init-rocketchat-client) 
  ;; https://stuartsierra.com/2015/08/10/clojure-donts-redundant-map
  ;; TODO: we should check that we didn't scheduled an already present reminder
  (run! schedule-job-and-triggers events-reminders)
  (daemonize))
