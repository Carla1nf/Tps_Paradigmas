--Telco

--Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
--Primero las ingresa al mapa de la región. 
--Luego establece vínculos entre ellas de cierta calidad y capacidad.
--Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
--capacidad por cada enlace recorrido.

--Para sostener este modelo se cuenta con las siguientes entidades:

module City ( City, newC, nameC, distanceC )
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP a b = Poi a b

cuadrado :: Int -> Float
cuadrado x = fromIntegral(x*x)

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x0 y0)(Poi x1 y1) =  sqrt(cuadrado(x0-x1)+cuadrado(y0-y1))
data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nombre lugar = Cit nombre lugar

nameC :: City -> String
nameC (Cit nombre _) = nombre

distanceC :: City -> City -> Float
distanceC (Cit nombre1 lugar1)(Cit nombre2 lugar2) = difP(lugar1)(lugar2)