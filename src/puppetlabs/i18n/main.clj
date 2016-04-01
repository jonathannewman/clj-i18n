(ns puppetlabs.i18n.main
  "Some I18N examples"
  (:gen-class)
  (:require [puppetlabs.i18n.core :as i18n :refer [tru trs]]))

;; Some simple examples of using tru/trs
;; The unit tests rely on the message catalog and translation generated for
;; the messages in this file. If you make changes to the messages here, you
;; might also have to change the tests.
(defn -main []
  ;; You have to use a literal string as the first argument to i18n/tr
  ;; This code ensures that translators never see this message since
  ;; xgettext doesn't see the string
  (let [dont-do-this "Current Locale: {0}"]
    (println (tru dont-do-this (.toString (i18n/user-locale)))))

  ;; Very simple localization
  (println (trs "Welcome! This is localized"))

  (println (trs "Welcome! This is localized"))

  ;; String with arguments
  (let [variants [[1 1] [2 2] [3 5] [7 1] [1 7]]]
    (doseq [[nprog nmonths] variants]

      (println (i18n/tru "It took {0} programmers {1} months to implement this"
                        nprog nmonths))
      ; first item is number of programmers, second is number of months
      (println (str (i18n/tru "It took {0} {1} to implement this"
                    (i18n/trun "one programmer" "{0} programmers" nprog)
                    (i18n/trun "one month" "{0} months" nmonths))))))


  ;; String with special formatting
  (let [nbikes 9000000]
    (println (i18n/tru "There are {0,number,integer} bicycles in Beijing"
                      nbikes))))
