module City ( City, newC, nameC, distanceC )
   where
import Point
data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nombre coordenadas = Cit nombre coordenadas

nameC :: City -> String
nameC (Cit nombre _) = nombre

distanceC :: City -> City -> Float
distanceC (Cit _ coordenadas1)(Cit _ coordenadas2) = difP coordenadas1 coordenadas2