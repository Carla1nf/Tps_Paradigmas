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
tunel_4 = newT [link_2]

region = newR
regionCreada = iniciarNuevaRegion region [paris, madrid, barcelona, londres, napoles, buenosAires] [link_1,link_2,link_3,link_4,link_5] [tunel_1]

t = [foundR region paris,foundR region madrid, foundR region barcelona, foundR region londres, foundR region napoles, foundR region buenosAires]

get = delayR regionCreada paris madrid

test = [distanceC madrid barcelona == 223.6068,

        delayL link_2 == 0.1,

        linksL madrid barcelona link_2 == True,
        linksL madrid paris link_2 == False,

        findLinkR regionCreada madrid barcelona == link_2,

        linkedR regionCreada madrid barcelona == True,
        linkedR regionCreada barcelona madrid == True,
        linkedR regionCreada barcelona paris == False,

        capacityL link_2 == 4,
        capacityL link_5 == 8,

        availableCapacityForR regionCreada barcelona madrid == 3,

        connectedR regionCreada paris buenosAires == True,

        delayQ cableTitanio == 0.1,
        delayQ cableCobre == 0.01,

        usesT link_2 tunel_4  == True,
        usesT link_1 tunel_4  == False,
        usesT link_1 tunel_2  == False,

        connectsT londres napoles tunel_2 == True,
        connectsT londres paris tunel_2 == False,
        connectsT londres paris tunel_4 == False

        ]

