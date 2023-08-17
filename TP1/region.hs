module Region ( Region, newR) --, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

import City 
import Point 
import Quality 
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)
newR :: [City] -> [Link] -> [Tunel] -> Region
newR (x:xs) (y:ys) (z:zs) = Reg (x:xs) (y:ys) (z:zs)

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg (x:xs) (y:ys) (z:zs)) ciudad = Reg (ciudad:xs) (y:ys) (z:zs) 

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg (x:xs) (y:ys) (z:zs)) ciudad_1 ciudad_2 calidad = Reg (x:xs) (newL ciudad_1 ciudad_2 calidad:ys)(z:zs)

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región


--connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
--linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades


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
region_1 = newR [barcelona,madrid,londres] [link_1,link_2,link_3] [tunel_1]