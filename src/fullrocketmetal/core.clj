(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [fullrocketmetal.reminders :as reminders]
            [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.simple :refer [schedule with-repeat-count with-interval-in-milliseconds]]
            [missile.chat :as chat])
   (:gen-class))

(defjob NoOpJob
  [ctx]
  (comment "Does nothing")
  (println "doing stuff")
  )

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))

;; we might call this in future with core.async for moment is ok.
(defn daemonize []
  (while true
    ;; todo watch if a file changed (conf) and perfom some operation
    ;; sleep 5 min
    (let [interval (* 2 60 1000)]
      (Thread/sleep interval))
    (println "sleeping 2 min")
    ;; print debug infos  ;; TODO
    (flush)
))
  ;; TODO: setup the time logic for scheduling message at given time. ( before 5 min etc)

  ;; TODO: in the main we should act like a daemon, f
  ;; 1) refresh/re-read configuration if something hafs changed
  ;; 2) send reminders and do other actions periodically. 
  ;; 3) print debug infos ( rate-limiting)
(defn -main []
  (init-rocketchat-client) 
    (let [s   (-> (qs/initialize) qs/start)
    job (j/build
          (j/of-type NoOpJob)
          (j/with-identity (j/key "jobs.noop.1")))
    trigger (t/build
              (t/with-identity (t/key "triggers.1"))
              (t/start-now)
              (t/with-schedule (schedule
                                 (with-repeat-count 10)
                                 (with-interval-in-milliseconds 200))))]
  (qs/schedule s job trigger))
  (daemonize))
