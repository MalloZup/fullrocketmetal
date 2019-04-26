(ns fullrocketmetal.scheduler
  (:require
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.conversion :as qc]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.cron :as qcron]
            [missile.chat :as chat]
            [missile.channels :as channels])
   (:gen-class))

(defn create-rocket-msg-cron-trigger [cron-schedule-time]
  (t/build
  (t/start-now)
   (t/with-schedule (qcron/schedule (qcron/cron-schedule cron-schedule-time)))))

(defjob rocket-message-job  [ctx]
  (let [data (qc/from-job-data ctx)
        ch-name (data "ch-name")
        text-message (data "text-message")]
    ;; (chat/sendMessage  (channels/get-channel-id ch-name) text-message)
    (println ch-name)
    (println text-message)))

(defn create-rocket-msg-job [list-jobs]
  (j/build
    (j/of-type rocket-message-job)
    (j/using-job-data {"ch-name" (:channel-name list-jobs) "text-message" (:message list-jobs)})))
