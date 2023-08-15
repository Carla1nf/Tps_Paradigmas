module City ( City, newC, nameC, distanceC )
   where
import Point
data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nombre lugar = Cit nombre lugar

nameC :: City -> String
nameC (Cit nombre _) = nombre

distanceC :: City -> City -> Float
distanceC (Cit nombre1 lugar1)(Cit nombre2 lugar2) = difP(lugar1)(lugar2)