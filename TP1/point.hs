module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP a b = Poi a b

cuadrado :: Int -> Float
cuadrado x = fromIntegral(x*x)

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x0 y0)(Poi x1 y1) =  sqrt(cuadrado(x0-x1)+cuadrado(y0-y1))