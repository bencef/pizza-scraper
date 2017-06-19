(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.jsoup/jsoup "1.10.3"]])

(task-options!
 repl {:server  true
       :init-ns 'pizza.core})
