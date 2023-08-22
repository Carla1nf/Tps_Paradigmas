module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City 
import Point 
import Quality 

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL ciudad_1 ciudad_2 calidad = Lin ciudad_1 ciudad_2 calidad
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL ciudad_a_verificar (Lin ciudad_1 ciudad_2 _) | ciudad_a_verificar == ciudad_1 = True
    | ciudad_a_verificar == ciudad_2 = True
    | otherwise = False
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL ciudad_a_verificar_1 ciudad_a_verificar_2 (Lin _ _ _) | ciudad_a_verificar_1 == ciudad_a_verificar_2 = error "Las ciudades son iguales"
linksL ciudad_a_verificar_1 ciudad_a_verificar_2 (Lin ciudad_1 ciudad_2 calidad) = connectsL ciudad_a_verificar_1 (Lin ciudad_1 ciudad_2 calidad) && connectsL ciudad_a_verificar_2 (Lin ciudad_1 ciudad_2 calidad)

capacityL :: Link -> Int 
capacityL (Lin _ _ calidad) = capacityQ calidad
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ calidad) = delayQ calidad


