(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
    [missile.config :as rocketchat]
    [missile.chat :as chat])))

;; TODO this should be called in a iteration
;;(def channel-id (channels/get-channel-id "rock"))

(def reminders
  ((clojure.edn/read-string (slurp "reminders.edn"))))

(defn send-message  []
  (rocketchat.config/set-config-from-file ".rocketchat.edn")
  ( println (channels/list!) )
  let
  ;; read channels name, message etc.
  ;; iterate here over a list of channels
  (chat/sendMessage channnel-id message))