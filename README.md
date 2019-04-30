![logo](doc/logotype-horizontal.png)

An event bot for rocketchat.

## Released versions:

for a released version, take a look at GitHub releases.

## High-level Description:

As user of a Rocketchat, you can automatize your regular meeting events with `fullrocketmetal` easy.

Fullrocketmetal let you schedule in a crontab regulary syntax, a regular `message` event/reminder, which will sent to a channel name of your server.


## Quickstart

Use either the released jar or the devel one

## Configuration

Make sure this 2 edn files are in the same directory to the JAR clojure file you execute.

1) create a `.rocketchat.edn` file in same dir of `fullrocketmetal jar` with following content:

```
{:credentials  {:username "myusername" :server-url "myserver-rocketchat-url.com" :token "my-token-rocketchat"}}
```

2) create a `event.edn` file in same dir of `fullrocketmetal` with following content:

the `cron-schedule` is a cron syntax time.

```
{:reminders [
             {:channel-name "clojure" :cron-schedule "0 0/1 * * * ?" :message "[autogenerated] @here i'm a fullrocket-message-autogenerated-running each minute.https://github.com/MalloZup/fullrocketmetal "},
             ;;{:channel-name "my-other-channel" :cron-schedule "0 0/1 * * * ?" :message "i'm just a text after the default message"}
             ,
      ]}
```

this will send to `channel-name` a `message` which is a reminder, and  you can specify the frequence of the message via `cron-schedule` which follow the crontab syntax.

## Build from src

Use `lein uberjar` to build the jar

Use `java -jar fullrocketmetal-VERSION-standalone.jar`.
 This will run `fullrocketmetal` 


# Technical infos:

This project is using rocketchat missile library and quartz scheduler.

( https://github.com/MalloZup/missile)
