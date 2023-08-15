module Tunel ( Tunel, newT, connectsT)-- usesT, delayT )
   where

import City 
import Point 
import Quality 
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT (x:xs) = Tun (x:xs)
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun (x:xs)) | linksL ciudad_a_verificar_1 ciudad_a_verificar_2 x = True
    | connectsL ciudad_a_verificar_1 x = connectsT ciudad_a_verificar_2 ciudad_a_verificar_2 (Tun xs)
    | connectsL ciudad_a_verificar_2 x = connectsT ciudad_a_verificar_1 ciudad_a_verificar_1 (Tun xs)
    | otherwise = connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun xs)
--usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
--delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel

madrid_location = newP 200 400
madrid = newC "madrid" madrid_location
paris_location = newP 1100 (-2100)
paris = newC "paris" paris_location
londres_location = newP 4000 2500
londres = newC "londres" londres_location
cable_titanio = newQ "Titanio" 4 0.1
link_1 = newL paris madrid cable_titanio
link_2 = newL madrid londres cable_titanio

link_3 = newL londres barcelona cable_titanio

barcelona_location = newP 100 200
barcelona = newC "barcelona" barcelona_location

tunel_1 = newT [link_1,link_2,link_3]