;; -*- indent-tabs-mode: nil -*-

(ns midje.midje-forms.t-dissecting
  (:require [clojure.zip :as zip])
  (:use [midje.midje-forms dissecting recognizing]
        [midje.error-handling monadic]
        [midje sweet test-util]))

(unfinished unused used)
(defn calls-nothing [] )

(unfinished local)
(defn calls-used [] (str (used) " " (local)))

(expect (separate-background-forms '[ (against-background) (f 1) => 3 ]) => [ [] '[ (f 1) => 3 ] ])



(tabular
 (fact "separate-background-forms divides forms into background and other things"
   (separate-background-forms '?in) => '[ ?background-forms  ?other-forms])

 ?in                                    ?background-forms               ?other-forms
 [ (against-background ..back1..
                       ..back2..)
   ..claim1..
   ..claim2..]                         [..back1.. ..back2..]          [..claim1.. ..claim2..]

 [ (against-background ..back1..)
   ..claim1..
   (against-background ..back2..)]      [..back1.. ..back2..]         [..claim1..] 

 []                                     []                              []
 [ (f 1) => 3 ]                         []                              [ (f 1) => 3 ]

 [(against-background)
  (f 1) => 3 ]                          []                              [(f 1) => 3]
)
