(ns qanda.views.about
  (use noir.core
       hiccup.core
       hiccup.page-helpers)
  (:require [qanda.views.layout :as layout])
  (:require [noir.response :as response]))

(defpartial about-label-1 []
  [:p "Ja sam Dusan Lilic, student Fakulteta Organizacionih Nauka. 
Ova mala web aplikacija je izradjena za potrebe ispita iz predmeta Savremene Softverske Arhitekture, kod prof. Dragana Djurica."])

(defpartial about-label-2 []
  [:p "Izradjena je u " (link-to "http://clojure.org/" "Clojure") " programskom jeziku, koristeci " (link-to "http://www.webnoir.org/" "Noir") " web framework."])


(defpage "/about" []
  (layout/main
    (about-label-1)
    (about-label-2)
    [:p {:class "answer-link"} "Lilic Dusan 312/07"]))