import City 
import Point 
import Quality 
import Link
import Tunel
import Region

madrid = newC "madrid" (newP 3000 2000)
paris = newC "paris" (newP 3500 2200)
londres = newC "londres" (newP 2500 4000)
barcelona = newC "barcelona" (newP 2800 1900)
napoles = newC "napoles" (newP 3000 3000)
buenosAires = newC "buenosAires" (newP (-2000) (-2500))

cableTitanio = newQ "Titanio" 4 0.1
cableCobre = newQ "Cobre" 8 0.01

link_1 = newL paris madrid cableTitanio
link_2 = newL madrid barcelona cableTitanio
link_3 = newL barcelona londres cableTitanio
link_4 = newL londres napoles cableCobre
link_5 = newL napoles buenosAires cableCobre

tunel_1 = newT [link_1,link_2,link_3,link_4,link_5]
tunel_2 = newT [link_5,link_4,link_3]
tunel_3 = newT [link_2,link_3,link_4,link_5]

region = newR

t = [foundR region paris,foundR region madrid, foundR region barcelona, foundR region londres, foundR region napoles, foundR region buenosAires]
test = [distanceC madrid barcelona == 223.6068,
        delayL link_2 == 0.1,
        linksL madrid barcelona link_2 == True]