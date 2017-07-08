(ns pizza.core
  (import [org.jsoup Jsoup]
          [org.jsoup.nodes Document Element])
  (require [clojure.string :as str])
  (:gen-class))

(def pizza-url "https://pizzaforte.hu/pizzak.php")

(defn parse-ingredients [ings]
  (-> ings
      (str/replace #"[()]" "")
      (str/split #", ")))

(defn parse-pizza [e]
  (let [text-of (fn [selector]
                  (.ownText (first (.select e selector))))]
    {:name (text-of "h3")
     :ingredients (parse-ingredients
                   (text-of "h3 + p"))}))

(defn pizza-selector [menu]
  (let [menus {:classic 1
               :italy   2
               :italien 2
               :fitness 3}]
    (str/join " " ["#body_bg"
                   "#content_container"
                   "#content"
                   (str "#pizzak_list_" (menus menu) ".product_list")
                   "tbody"
                   "tr"
                   "td"
                   ".product_info_panel"])))

(defn select-pizzas [^String selector]
  (-> (Jsoup/connect pizza-url)
      (.get)
      (.select selector)))

(defn get-pizzas [crust-type]
  (map parse-pizza
       (select-pizzas (pizza-selector crust-type))))

(defn filter-ingredients [re ps]
  (filter #(some (fn [s] (re-find re s)) (:ingredients %)) ps))
