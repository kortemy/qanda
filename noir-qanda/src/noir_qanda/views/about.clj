(ns qanda.views.about
  (:require [qanda.views.layout :as common])
  (:use [noir.core :only [defpage]]))

(defpage "/about" []
         (common/layout
           [:h1 "hello from about"]))
