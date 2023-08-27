module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR, iniciarNuevaRegion, findLinkR)
   where

import City 
import Point 
import Quality 
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Eq,Show)
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg ciudades links tuneles) ciudad = Reg (ciudad:ciudades) links tuneles 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg ciudades links tuneles) ciudad_1 ciudad_2 calidad = Reg ciudades (newL ciudad_1 ciudad_2 calidad:links) tuneles

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg ciudades links tuneles) ciudades_a_comunicar = Reg ciudades links (newT (crearListaDeLinksR (Reg ciudades links tuneles) ciudades_a_comunicar):tuneles)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) ciudad1 ciudad2 = any (connectsT ciudad1 ciudad2) tuneles

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ [] _ ) _ _ = False
linkedR (Reg ciudades (link:links) tuneles) ciudad_1 ciudad_2 | linksL ciudad_1 ciudad_2 link = True
   | otherwise = linkedR (Reg ciudades links tuneles) ciudad_1 ciudad_2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg ciudades links (tunel:tuneles)) ciudad_1 ciudad_2 | connectsT ciudad_1 ciudad_2 tunel = delayT tunel
   | otherwise = delayR (Reg ciudades links tuneles) ciudad_1 ciudad_2

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR reg ciudad_1 ciudad_2 = capacityL (findLinkR reg ciudad_1 ciudad_2) - capacidadUtilizadaR reg ciudad_1 ciudad_2 0

-----
crearListaDeLinksR :: Region -> [City] -> [Link]
crearListaDeLinksR _ [] = []
crearListaDeLinksR _ [_] =[]
crearListaDeLinksR (Reg ciudades links tuneles) (ciudad1:ciudad2:tail_ciudades) = [link |link <- links,linksL ciudad1 ciudad2 link] ++ crearListaDeLinksR (Reg ciudades links tuneles) (ciudad2:tail_ciudades)

capacidadUtilizadaR :: Region -> City -> City -> Int -> Int
capacidadUtilizadaR (Reg ciudades links []) ciudad_1 ciudad_2 capacidad_minima = capacidad_minima
capacidadUtilizadaR (Reg ciudades links (tunel:tuneles)) ciudad_1 ciudad_2 capacidad_minima | usesT (findLinkR (Reg ciudades links (tunel:tuneles)) ciudad_1 ciudad_2) tunel = capacidadUtilizadaR (Reg ciudades links tuneles) ciudad_1 ciudad_2 (capacidad_minima + 1)
   |otherwise = capacidadUtilizadaR (Reg ciudades links tuneles) ciudad_1 ciudad_2 capacidad_minima

findLinkR :: Region -> City -> City -> Link 
findLinkR (Reg _ [] _) _ _ = error "No existe link entre estas dos ciudades"
findLinkR (Reg ciudades (link:links) tuneles) ciudad_1 ciudad_2 | linksL ciudad_1 ciudad_2 link = link
   | otherwise = findLinkR (Reg ciudades links tuneles) ciudad_1 ciudad_2 