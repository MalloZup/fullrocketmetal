(ns fullrocketmetal.reminders)

(defn events []
  (clojure.edn/read-string (slurp "events.edn")))

(defn get-reminders []
  (get-in (events) [:reminders]))
