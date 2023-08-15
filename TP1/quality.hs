module Quality ( Quality, newQ, capacityQ, delayQ )
   where

import Point
import City
data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ calidad capacidad delay = Qua calidad capacidad delay
capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacidad _) = capacidad 
delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay