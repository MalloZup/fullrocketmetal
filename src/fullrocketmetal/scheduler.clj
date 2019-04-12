(ns fullrocketmetal.scheduler
  (:require [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.cron :refer [schedule cron-schedule]]))

(defjob NoOpJob
  [ctx]
  (comment "Does nothing"))

(defn trigger-job []
  (let [s   (-> (qs/initialize) qs/start)
       job  (j/build
              (j/of-type NoOpJob)
              (j/with-identity (j/key "jobs.noop.1")))
        trigger (t/build
                  (t/with-identity (t/key "triggers.1"))
                  (t/start-now)
                  (t/with-schedule (schedule
                                     (cron-schedule "0 0 15 ? * 5"))))]
  (qs/schedule s job trigger)))
