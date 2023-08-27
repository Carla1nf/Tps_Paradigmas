module Test 
    where
import City 
import Point 
import Quality 
import Link
import Tunel
import Region


-----
madridLocation = newP 3000 2000
parisLocation = newP 3500 2200
londresLocation = newP 2500 4000
barcelonaLocation = newP 2800 1900
napolesLocation = newP 3000 3000
romaLocation = newP 2000 2500
-----
madrid = newC "madrid" madridLocation
paris = newC "paris" parisLocation
londres = newC "londres" londresLocation
barcelona = newC "barcelona" barcelonaLocation
napoles = newC "napoles" napolesLocation
roma = newC "roma" romaLocation
----
cableTitanio = newQ "Titanio" 4 0.5
cableCobre = newQ "Cobre" 6 0.25
cableResistente = newQ "Resistente" 8 0.1
-----
link_1 = newL paris madrid cableTitanio
link_2 = newL madrid barcelona cableCobre
link_3 = newL barcelona londres cableResistente
link_4 = newL londres napoles cableCobre
link_5 = newL napoles roma cableTitanio
-----
tunel_1 = newT [link_1,link_2,link_3,link_4,link_5]
tunel_2 = newT [link_5,link_4,link_3]
tunel_3 = newT [link_2,link_3,link_4,link_5]
-----
anadirCiudades :: Region -> [City] -> Region
anadirCiudades region [] = region
anadirCiudades region (ciudad:ciudades) = foundR (anadirCiudades region ciudades) ciudad  

newEuropa = newR
europa1 = anadirCiudades newEuropa [paris,madrid,barcelona,londres,napoles,roma]
europa2 = linkR europa1 paris madrid cableTitanio
europa3 = linkR europa2 madrid barcelona cableCobre
europa4 = linkR europa3 barcelona londres cableResistente
europa5 = linkR europa4 londres napoles cableCobre
europa6 = linkR europa5 napoles roma cableTitanio
europa7 = tunelR europa6 [paris,madrid,barcelona,londres,napoles,roma]
europa = tunelR europa7 [barcelona,londres,napoles,roma]
-----
test_point = [difP madridLocation parisLocation == 538.5165]

test_city = [distanceC madrid barcelona == 223.6068,
            nameC madrid == "madrid"]

test_calidad = [capacityQ cableTitanio == 4,
                delayQ cableCobre == 0.25]

test_link = [delayL link_2 == 0.25,
            connectsL barcelona link_3,
            linksL barcelona londres link_3,
            capacityL link_4 == 6]

test_tunel = [connectsT paris roma tunel_1,
            usesT link_4 tunel_2,
            delayT tunel_3 == 1.1]

test_region = [connectedR europa barcelona roma,
            linkedR europa napoles roma,
            delayR europa paris roma == 1.6,
            availableCapacityForR europa barcelona londres == 6]

test = [test_point,test_city,test_calidad,test_link,test_tunel,test_region]