--Telco

--Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
--Primero las ingresa al mapa de la región. 
--Luego establece vínculos entre ellas de cierta calidad y capacidad.
--Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
--capacidad por cada enlace recorrido.

--Para sostener este modelo se cuenta con las siguientes entidades:

module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP a b = Poi a b

cuadrado :: Int -> Float
cuadrado x = fromIntegral(x*x)

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x0 y0)(Poi x1 y1) =  sqrt(cuadrado(x0-x1)+cuadrado(y0-y1))
-----------------
module City ( City, newC, nameC, distanceC )
   where

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nombre lugar = Cit nombre lugar

nameC :: City -> String
nameC (Cit nombre _) = nombre

distanceC :: City -> City -> Float
distanceC (Cit nombre1 lugar1)(Cit nombre2 lugar2) = difP(lugar1)(lugar2)
-----------------
module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ calidad capacidad delay = Qua calidad capacidad delay
capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (newQ _ capacidad _) = capacidad 
delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (newQ _ _ delay)
-------------------
module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL ciudad_1 ciudad_2 cable = Lin ciudad_1 ciudad_2 cable
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL ciudad_a_verificar (Lin ciudad_1 ciudad_2 _) | ciudad_a_verificar == ciudad_1 = True
    | ciudad_a_verificar == ciudad_2 = True
    | otherwise = False
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL ciudad_a_verificar_1 ciudad_a_verificar_2 (Lin ciudad_1 ciudad_2 cable) = connectsL ciudad_a_verificar_1 (Lin ciudad_1 ciudad_2 cable) && connectsL ciudad_a_verificar_2 (Lin ciudad_1 ciudad_2 cable)
capacityL :: Link -> Int 
capacityL (Lin _ _ cable) = capacityQ cable
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ cable) = delayQ cable
-------------------
newT :: [Link] -> Tunel
newT (x:xs) = Tun (x:xs)
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun (x:xs)) | linksL ciudad_a_verificar_1 ciudad_a_verificar_2 x = True
    | connectsL ciudad_a_verificar_1 x = connectsT ciudad_a_verificar_2 ciudad_a_verificar_2 (Tun xs)
    | connectsL ciudad_a_verificar_2 x = connectsT ciudad_a_verificar_1 ciudad_a_verificar_1 (Tun xs)
    | otherwise = connectsT ciudad_a_verificar_1 ciudad_a_verificar_2 (Tun xs)
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT _ (Tun []) = False
usesT link (Tun (x:xs)) | link == x = True
    | otherwise = usesT link (Tun xs)
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (x:xs)) = delayL x + delayT (Tun xs)
-------------------
module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg 
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunnels) city = Reg (city : cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) city1 city2 quality =
    let newLink = newL city1 city2 quality
    in Reg cities (newLink : links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) ciudad1 ciudad2 = any (\tunel -> connectsT ciudad1 ciudad2 tunel) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR reg ciudad1 ciudad2 = any (\link -> connectsL ciudad1 link || connectsL ciudad2 link) (linksForR reg)

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR reg ciudad1 ciudad2 = sum (map delayL (linksFor reg ciudad2))

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades


