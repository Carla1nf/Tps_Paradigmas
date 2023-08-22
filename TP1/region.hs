module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR) --availableCapacityForR, findLink)
   where

import City 
import Point 
import Quality 
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg ciudades links tuneles) ciudad = Reg (ciudad:ciudades) links tuneles 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg ciudades links tuneles) ciudad_1 ciudad_2 calidad = Reg ciudades (newL ciudad_1 ciudad_2 calidad:links) tuneles

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg ciudades links tuneles) ciudades_a_comunicar = Reg ciudades links (newT links:tuneles)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) ciudad1 ciudad2 = any (connectsT ciudad1 ciudad2) tuneles

--connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
--connectedR (Reg _ _ []) ciudad_1 ciudad_2 = False
--connectedR (Reg ciudades links (tunel:tuneles)) ciudad_1 ciudad_2 | connectsT ciudad_1 ciudad_2 tunel = True
--   |otherwise = connectedR (Reg ciudades links tuneles) ciudad_1 ciudad_2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _ ) _ _ = False
linkedR (Reg ciudades (link:links) tuneles) ciudad_1 ciudad_2 | linksL ciudad_1 ciudad_2 link = True
   | otherwise = linkedR (Reg ciudades links tuneles) ciudad_1 ciudad_2

--linkedR (Reg ciudades links tuneles) ciudad_1 ciudad_2 = any (\link -> connectsL ciudad_1 link || connectsL ciudad_2 link) tuneles

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg ciudades links (tunel:tuneles)) ciudad_1 ciudad_2 | connectsT ciudad_1 ciudad_2 tunel = delayT tunel
   | otherwise = delayR (Reg ciudades links tuneles) ciudad_1 ciudad_2

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
--availableCapacityForR (Reg ciudades links tuneles) ciudad_1 ciudad_2 = 

findLink :: Region -> City -> City -> Link
findLink (Reg ciudades (link:links) tuneles) ciudad_1 ciudad_2 | linksL ciudad_1 ciudad_2 link = link
   | otherwise = findLink (Reg ciudades links tuneles) ciudad_1 ciudad_2 

--findLink / linksL
--capacityL
--usesT










--delayR reg ciudad1 ciudad2 = sum (map delayL (linksForR reg ciudad1 ciudad2))

-- delayR ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun []) = False
-- delayR ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun (x:xs)) | linksL ciudad_a_verificar_1 ciudad_a_verificar_2 x = True
--     | connectsL ciudad_a_verificar_1 x = delayR ciudad_a_verificar_2 ciudad_a_verificar_2 (Tun xs)
--     | connectsL ciudad_a_verificar_2 x = delayR ciudad_a_verificar_1 ciudad_a_verificar_1 (Tun xs)
--     | otherwise = delayR ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun xs)

-- delayR (Reg ciudades (link:links) tuneles) ciudad_1 ciudad_2 | linksL ciudad_1 ciudad_2 link = delayL link
--    | connectsL ciudad_1 link = delayR (Reg ciudades links tuneles) ciudad_2 ciudad_2
--    | connectsL ciudad_2 link = delayR (Reg ciudades links tuneles) ciudad_1 ciudad_1 
--    | otherwise = delayR (Reg ciudades links tuneles) ciudad_1 ciudad_2

roma_location = newP 900 (-1100)
roma = newC "roma" roma_location
madrid_location = newP 200 400
madrid = newC "madrid" madrid_location
paris_location = newP 1100 (-2100)
paris = newC "paris" paris_location
londres_location = newP 4000 2500
londres = newC "londres" londres_location
cable_titanio = newQ "Titanio" 4 0.1
link_1 = newL paris madrid cable_titanio
link_2 = newL madrid barcelona cable_titanio

link_3 = newL barcelona londres cable_titanio

barcelona_location = newP 100 200
barcelona = newC "barcelona" barcelona_location

cable_cobre = newQ "Cobre" 8 0.01

link_4 = newL madrid barcelona cable_cobre


--stickWith :: [Int] -> Stick
--stickWith = foldr (flip push) Vacio
tunel_1 = newT [link_1,link_2,link_3]
region_1 = iniciarNuevaRegion region [barcelona,madrid,londres] [link_1,link_2,link_3] [tunel_1]
region = newR

-- initWith :: [Int] -> [Int] -> [Int] -> Hanoi
-- initWith i c d = Hanoi (stickWith i) (stickWith c) (stickWith d)

-- addCities :: Region -> [City] -> Region
-- addCities (Reg [] links tuneles) ciudades_a_agregar = Reg ciudades_a_agregar links tuneles
-- addLinks :: Region -> [Link] -> Region
-- addLinks (Reg ciudades [] tuneles) links_a_agregar = Reg ciudades links_a_agregar tuneles
-- addTunels :: Region -> [Tunel] -> Region
-- addTunels (Reg ciudades links []) tuneles_a_agregar = Reg ciudades links  tuneles_a_agregar


iniciarNuevaRegion :: Region -> [City] -> [Link] -> [Tunel] -> Region
iniciarNuevaRegion (Reg [] [] [])= Reg

--iniciarNuevaRegion reg (ciudad:ciudades) (link:links) (tunel:tuneles) | (foundR reg ciudad) 
--   |(link:links) = (linkR reg link) 
--   |(tunel:tuneles) = (tunelR reg tunel)
