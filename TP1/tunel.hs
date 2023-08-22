module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import City
import Point 
import Quality 
import Link
import System.Win32.DebugApi (continueDebugEvent)

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun 
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun []) = False
--connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun (link:links)) | linksL ciudad_a_verificar_1 ciudad_a_verificar_2 link = True
--    | connectsL ciudad_a_verificar_1 link = connectsT ciudad_a_verificar_2 ciudad_a_verificar_2 (Tun links)
--    | connectsL ciudad_a_verificar_2 link = connectsT ciudad_a_verificar_1 ciudad_a_verificar_1 (Tun links)
--    | otherwise = connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun links)
connectsT ciudad_1 ciudad_2 (Tun (link:links)) | (connectsL ciudad_1 link || connectsL ciudad_2 link) && (connectsL ciudad_1 (last links) || connectsL ciudad_2 (last links)) = True
    |otherwise = False
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT _ (Tun []) = False
usesT link_a_verificar (Tun (link:links)) | link_a_verificar == link = True
    | otherwise = usesT link_a_verificar (Tun links)
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (link:links)) = delayL link + delayT (Tun links)

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

cable_cobre = newQ "Cobre" 8 0.01

link_4 = newL madrid barcelona cable_cobre

tunel_1 = newT [link_1,link_2,link_3]