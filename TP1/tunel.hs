module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import City 
import Point 
import Quality 
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun []) = False
connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun _)| ciudad_a_verificar_1 == ciudad_a_verificar_2 = error "Mismas ciudades como input"
connectsT ciudad_1 ciudad_2 (Tun (link:links)) | (connectsL ciudad_1 link || connectsL ciudad_2 link) && (connectsL ciudad_1 (last links) || connectsL ciudad_2 (last links)) = True
    | otherwise = False

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT _ (Tun []) = False
usesT link_a_verificar (Tun (link:links)) | link_a_verificar == link = True
    | otherwise = usesT link_a_verificar (Tun links)

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (link:links)) = delayL link + delayT (Tun links)
