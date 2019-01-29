# pizza-scraper
Web scraper for a pizza place.

# Usage
I use this from a [CIDER](https://docs.cider.mx/en/latest/) REPL from Emacs.

    M-x cider-jack-in
    (use 'pizza.core)
    ,ns
    pizza.core RET

Then use it like this:

```clojure
(defonce ^{:dynamic true} *all* (get-pizzas :classic))
(->> *all*
  (filter-ingredients #"sajt|mozzarella")
  (filter-ingredients #"oliva"))
```
