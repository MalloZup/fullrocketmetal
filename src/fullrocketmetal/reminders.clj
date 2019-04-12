(ns fullrocketmetal.reminders
  (:require [missile.channels :as channels]
            [missile.chat :as chat]))

(defn events []
  (clojure.edn/read-string (slurp "events.edn")))

(defn get-reminders []
  (get-in (events) [:reminders]))

(defn send-single-reminder [reminder-map]
  (chat/sendMessage (missile.channels/get-channel-id (:channel-name reminder-map)) (:message reminder-map)))

;; TODO: would be nice to send this messages in parallel (futures) or investigate of other solutions
(defn send-all-reminders []
  (map send-single-reminder (get-reminders)))

;; send all messages
(defn send-reminders []
  "dispatch message/reminder to rocketchat based on configuration"
  (send-all-reminders))

