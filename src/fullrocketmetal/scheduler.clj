(ns fullrocketmetal.scheduler
  (:require
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.cron :as qcron]
            [missile.chat :as chat])
   (:gen-class))

(defn create-rocket-msg-cron-trigger []
  (t/build
    ;; TODO: think later on UID if needed
    ;; (t/with-identity (t/key "triggers.1"))
    (t/start-now)
    (t/with-schedule (qcron/schedule  (qcron/cron-schedule "0 0/1 * * * ?"))))) 

