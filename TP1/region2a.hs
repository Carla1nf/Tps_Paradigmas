module Region ( Region, newR, foundR, linkR, connectedR, linkedR, delayR) --availableCapacityForR, usedCapacityForR )
   where

import Point
import City 
import Quality 
import Link 
import Tunel


data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg [city][link][tunel]
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunnels) city = Reg (city : cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) city1 city2 quality =
    let newLink = newL city1 city2 quality
    in Reg cities (newLink : links) tunnels

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) ciudad1 ciudad2 = any (\tunel -> connectsT ciudad1 ciudad2 tunel) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR reg ciudad1 ciudad2 = any (\link -> connectsL ciudad1 link || connectsL ciudad2 link) (linksForR reg)

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR reg ciudad1 ciudad2 = sum (map delayL (linksForR reg ciudad2))

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades