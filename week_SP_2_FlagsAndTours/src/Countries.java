import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ImageIcon;

public class Countries
{
    public static final Map<String, String> CAPITALS = new TreeMap<String, String>() {{
            put("afghanistan", "Kabul");
            put("albania", "Tirana");
            put("algeria", "Algiers");
            put("american samoa", "Pago Pago");
            put("andorra", "Andorra la Vella");
            put("angola", "Luanda");
            put("anguilla", "The Valley");
            put("antigua and barbuda", "Saint John's");
            put("argentina", "Buenos Aires");
            put("armenia", "Yerevan");
            put("aruba", "Oranjestad");
            put("australia", "Canberra");
            put("austria", "Vienna");
            put("azerbaijan", "Baku");
            put("bahamas", "Nassau");
            put("bahrain", "Manama");
            put("bangladesh", "Dhaka");
            put("barbados", "Bridgetown");
            put("belarus", "Minsk");
            put("belgium", "Brussels");
            put("belize", "Belmopan");
            put("benin", "Porto-Novo");
            put("bermuda", "Hamilton");
            put("bhutan", "Thimphu");
            put("bolivia", "La Paz");
            put("bosnia and herzegovina", "Sarajevo");
            put("botswana", "Gaborone");
            put("brazil", "Brasilia");
            put("british indian ocean territory", "Diego Garcia");
            put("british virgin islands", "Road Town");
            put("brunei", "Bandar Seri Begawan");
            put("bulgaria", "Sofia");
            put("burkina faso", "Ouagadougou");
            put("burma", "Naypyidaw");
            put("burundi", "Bujumbura");
            put("cambodia", "Phnom Penh");
            put("cameroon", "Yaounde");
            put("canada", "Ottawa");
            put("cape verde", "Praia");
            put("cayman islands", "George Town");
            put("central african republic", "Bangui");
            put("chad", "N'Djamena");
            put("chile", "Santiago");
            put("china", "Beijing");
            put("colombia", "Bogota");
            put("comoros", "Moroni");
            put("congo democratic republic", "Kinshasa");
            put("congo republic", "Brazzaville");
            put("cook islands", "Avarua");
            put("costa rica", "San Jose");
            put("cote divoire", "Yamoussoukro");
            put("croatia", "Zagreb");
            put("cuba", "Havana");
            put("cyprus", "Nicosia");
            put("czech republic", "Prague");
            put("denmark", "Copenhagen");
            put("djibouti", "Djibouti");
            put("dominica", "Roseau");
            put("dominican republic", "Santo Domingo");
            put("east timor", "Dili");
            put("egypt", "Cairo");
            put("el salvador", "San Salvador");
            put("england", "London");
            put("equador", "Quito");
            put("equatorial guinea", "Malabo");
            put("eritrea", "Asmara");
            put("estonia", "Tallinn");
            put("ethiopia", "Addis Ababa");
            put("falkland islands", "Stanley");
            put("faroe islands", "Torshavn");
            put("fiji", "Suva");
            put("finland", "Helsinki");
            put("france", "Paris");
            put("french polynesia", "Papeete");
            put("gabon", "Libreville");
            put("gambia", "Banjul");
            put("georgia", "Tbilisi");
            put("germany", "Berlin");
            put("ghana", "Accra");
            put("gibraltar", "Gibraltar");
            put("great britain", "London");
            put("greece", "Athens");
            put("greenland", "Nuuk");
            put("grenada", "St. George's");
            put("guam", "Hagatna");
            put("guatemala", "Guatemala City");
            put("guernsey", "Saint Peter Port");
            put("guinea", "Conakry");
            put("guinea bissau", "Bissau");
            put("guyana", "Georgetown");
            put("haiti", "Port-au-Prince");
            put("honduras", "Tegucigalpa");
            put("hong kong", "Hong Kong");
            put("hungary", "Budapest");
            put("iceland", "Reykjavik");
            put("india", "New Delhi");
            put("indonesia", "Jakarta");
            put("iran", "Tehran");
            put("iraq", "Baghdad");
            put("ireland", "Dublin");
            put("isle of man", "Douglas");
            put("israel", "Jerusalem");
            put("italy", "Rome");
            put("jamaica", "Kingston");
            put("japan", "Tokyo");
            put("jersey", "Saint Helier");
            put("jordan", "Amman");
            put("kazakhstan", "Astana");
            put("kenya", "Nairobi");
            put("kiribati", "Tarawa");
            put("kuwait", "Kuwait City");
            put("kyrgyzstan", "Bishkek");
            put("laos", "Vientiane");
            put("latvia", "Riga");
            put("lebanon", "Beirut");
            put("lesotho", "Maseru");
            put("liberia", "Monrovia");
            put("libya", "Tripoli");
            put("liechtenstein", "Vaduz");
            put("lithuania", "Vilnius");
            put("luxembourg", "Luxembourg");
            put("macau", "N/A");
            put("macedonia", "Skopje");
            put("madagascar", "Antananarivo");
            put("malawi", "Lilongwe");
            put("malaysia", "Kuala Lumpur");
            put("maledives", "Male");
            put("mali", "Bamako");
            put("malta", "Valletta");
            put("marshall islands", "Majuro");
            put("martinique", "Fort-de-France");
            put("mauretania", "Nouakchott");
            put("mauritius", "Port Louis");
            put("mexico", "Mexico City");
            put("micronesia", "Palikir");
            put("moldova", "Chisinau");
            put("monaco", "Monaco");
            put("mongolia", "Ulaanbaatar");
            put("montserrat", "Plymouth");
            put("morocco", "Rabat");
            put("mozambique", "Maputo");
            put("namibia", "Windhoek");
            put("nauru", "Yaren");
            put("nepal", "Kathmandu");
            put("netherlands", "Amsterdam");
            put("netherlands antilles", "Willemstad");
            put("new zealand", "Wellington");
            put("nicaragua", "Managua");
            put("niger", "Niamey");
            put("nigeria", "Abuja");
            put("niue", "Alofi");
            put("norfolk island", "Kingston");
            put("northern mariana islands", "Saipan");
            put("north korea", "Pyongyang");
            put("norway", "Oslo");
            put("oman", "Muscat");
            put("pakistan", "Islamabad");
            put("palau", "Melekeok");
            put("panama", "Panama City");
            put("papua new guinea", "Port Moresby");
            put("paraquay", "Asuncion");
            put("peru", "Lima");
            put("philippines", "Manila");
            put("pitcairn islands", "Adamstown");
            put("poland", "Warsaw");
            put("portugal", "Lisbon");
            put("puerto rico", "San Juan");
            put("qatar", "Doha");
            put("romania", "Bucharest");
            put("russia", "Moscow");
            put("rwanda", "Kigali");
            put("saint helena", "Jamestown");
            put("saint kitts and nevis", "Basseterre");
            put("saint lucia", "Castries");
            put("saint pierre and miquelon", "Saint-Pierre");
            put("saint vincent and the grenadines", "Kingstown");
            put("samoa", "Apia");
            put("san marino", "San Marino");
            put("sao tome and principe", "Sao Tome");
            put("saudi arabia", "Riyadh");
            put("scotland", "Edinburgh");
            put("senegal", "Dakar");
            put("serbia montenegro", "Belgrade");
            put("seychelles", "Victoria");
            put("sierra leone", "Freetown");
            put("singapore", "Singapore");
            put("slovakia", "Bratislava");
            put("slovenia", "Ljubljana");
            put("solomon islands", "Honiara");
            put("somalia", "Mogadishu");
            put("south africa", "Pretoria");
            put("south georgia", "King Edward Point");
            put("south korea", "Seoul");
            put("spain", "Madrid");
            put("sri lanka", "Colombo");
            put("sudan", "Khartoum");
            put("suriname", "Paramaribo");
            put("swaziland", "Mbabane");
            put("sweden", "Stockholm");
            put("switzerland", "Bern");
            put("syria", "Damascus");
            put("taiwan", "Taipei");
            put("tajikistan", "Dushanbe");
            put("tanzania", "Dar es Salaam");
            put("thailand", "Bangkok");
            put("tibet", "Lhasa");
            put("togo", "Lome");
            put("tonga", "Nuku'alofa");
            put("trinidad and tobago", "Port of Spain");
            put("tunisia", "Tunis");
            put("turkey", "Ankara");
            put("turkmenistan", "Ashgabat");
            put("turks and caicos islands", "Grand Turk");
            put("tuvalu", "Funafuti");
            put("uganda", "Kampala");
            put("ukraine", "Kyiv");
            put("united arab emirates", "Abu Dhabi");
            put("uruquay", "Montevideo");
            put("usa", "Washington");
            put("uzbekistan", "Tashkent");
            put("vanuatu", "Port-Vila");
            put("vatican city", "Vatican City");
            put("venezuela", "Caracas");
            put("vietnam", "Hanoi");
            put("virgin islands", "Charlotte Amalie");
            put("wales", "Cardiff");
            put("wallis and futuna", "Mata-Utu");
            put("yemen", "Sanaa");
            put("zambia", "Lusaka");
            put("zimbabwe", "Harare");
}};
    
    
    public static final Map<String, ImageIcon> FLAGS = getImageMap();

    private static Map<String, ImageIcon> getImageMap()
    {
        HashMap<String, ImageIcon> imageMap=new HashMap<>();
        CAPITALS.forEach((k,v)->
        {
            String kTokenized=k.replaceAll(" ", "_");
            imageMap.put(k, new ImageIcon(("shadow/"+"flag_"+kTokenized+".png")));
        });
        return imageMap;
    }
    
}