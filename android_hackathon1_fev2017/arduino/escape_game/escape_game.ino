
#include <ESP8266WiFi.h>

#define WIFI_CONNECTION_ATTEMPTS  3
const char* SSID = /*"AndroidAP";*/ /*"home";*/ "WCS";
const char* PASS = /*"1234567890";*/ /*"wifi@home";*/ "jecode4toulouse";

struct location {
  double lat;
  double lon;
  const char* secret;
};

// SHANGHAI
/*auiahugfiezghifdijgidjfgijfd
adfnigfdjngfd
adnfgidzf
sjdignfdsjngfdsngjkfdsg
dsjfngnfdsgjsfdg
*/
const struct location SHANGHAI_LOC = {
  43.602171,
  1.446822,
  "shanghai"
};

//Si l'herbe t'excite !
const struct location PRAIRIE_LOC = {
  43.595175,
  1.436717,
  "prairie"
};

// Si tu aimes la pisse et les clodos et la tise
const struct location WILSON_LOC = {
  43.604786,
  1.447403,
  "wilson"
};


const struct location DANS_TON_CUL = {
  43.604723,
  1.447203,
  "CUL"
};

const struct location TARGET_LOCATION = WILSON_LOC;


/*jena louis de la tour de mon miraille*/

const char* WEB_PAGE = "<!-- #######  YAY, YOU'RE CLEVER! #########-->\r\n<h1 style=\"color: #5e9ca0;\">Welcome to my <span style=\"color: #2b2301;\">documentation</span> !</h1>\r\n<h2 style=\"color: #2e6c80;\">How to find my protocol:</h2>\r\n<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\r\n<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://d30y9cdsu7xlg0.cloudfront.net/png/77784-200.png\" alt=\"interactive connection\" width=\"45\" /> Start with 20 Squats</li>\r\n<li style=\"clear: both;\"><img style=\"float: left;\" src=\"http://www.clipartkid.com/images/125/push-up-20clipart-clipart-panda-free-clipart-images-kYOE0A-clipart.png\" alt=\"html cleaner\" width=\"45\" /> Carry on with 25 Push-ups</li>\r\n<li style=\"clear: both;\"><img style=\"float: left;\" src=\"http://uoah.org/workout/images/Burpees.gif\" alt=\"Word to html\" width=\"45\" /> Now do 15 Burpees</li>\r\n<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://cdn1.iconfinder.com/data/icons/athlete-sport-exercise/301/athlete_pictogram_sport_sit_up_workout-512.png\" alt=\"replace text\" width=\"45\" /> 20 sit-ups to finish !</li>\r\n</ol>\r\n<!-- ####### http://emarmounier.sytes.net:5000/sharing/jWv1iszSC #########-->\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n\r\n<p><strong>&nbsp;</strong></p>\r\n<strong>Enjoy little Wilders!</strong></p>\r\n<p><strong>&nbsp;</strong></p>";

int status = WL_IDLE_STATUS;
WiFiServer server(80);
boolean webServerEnabled = false;
bool foundLocation = false;

double haverSine(double lat1, double lon1, double lat2, double lon2) {
  double ToRad = PI / 180.0;
  double R = 6371;   // radius earth in Km
  
  double dLat = (lat2-lat1) * ToRad;
  double dLon = (lon2-lon1) * ToRad; 
  
  double a = sin(dLat/2) * sin(dLat/2) +
       cos(lat1 * ToRad) * cos(lat2 * ToRad) * 
       sin(dLon/2) * sin(dLon/2); 
       
  double c = 2 * atan2(sqrt(a), sqrt(1-a)); 
  
  double d = R * c;
  return d;
}

class CommandHandler {
  public:
  virtual bool handleData(String data) = 0;
  void replyError(String response) {
    Serial.println("-ERROR=" + response);
  }
  void replyOk() {
    Serial.println("-OK");
  }
  void replyOk(String response) {
    Serial.println("-OK=" + response);
  }
};

class WifiCommandHandler : public CommandHandler {
  public:
  virtual bool handleData(String data) {
    if (data.equals("+ENABLE_WIFI")) {
      return handleEnableWifi();
    }
    if (data.equals("+DISABLE_WIFI")) {
      return handleDisableWifi();
    }
    return false;
  }
  bool handleDisableWifi() {
    WiFi.disconnect();
    while (status == WL_CONNECTED) {
      delay(1000);
    }
    replyOk();
    return true;
  }
  bool handleEnableWifi() {
    // check for the presence of the shield:
    if (WiFi.status() == WL_NO_SHIELD) {
      replyError("WiFi shield not present");
      return true;
    }

    int attempts = WIFI_CONNECTION_ATTEMPTS;
    // attempt to connect to Wifi network:
    while (status != WL_CONNECTED && attempts-- > 0) {
      status = WiFi.begin(SSID, PASS);    
      // wait 10 seconds for connection:
      delay(10000);
    }
    if (status == WL_CONNECTED) {
      IPAddress ip = WiFi.localIP();
      char buff[64];
      sprintf(buff, "%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
      replyOk("Wifi connected, IP " + String(buff));
    } else {
      replyError("Could not connect to Wifi AP");
    }
    return true;
  }
};

class WebServerCommandHandler : public CommandHandler {
  public:
  virtual bool handleData(String data) {
    if (data.equals("+ENABLE_WEB_SERVER")) {
      webServerEnabled = true;
      server.begin();
      replyOk();
      return true;
    }
    if (data.equals("+DISABLE_WEB_SERVER")) {
      webServerEnabled = false;
      server.stop();
      replyOk();
      return true;
    }
    return false;
  }
};

class LocationCommandHandler : public CommandHandler {
  public:
  virtual bool handleData(String data) {
    if (data.startsWith("+CHECK_LOCATION=")) {
      int eqPos = data.indexOf("=");
      if (eqPos >= 0) {
        String locStr = data.substring(eqPos + 1);
        int commaPos = locStr.indexOf(",");
        if (commaPos >= 0) {
          String latStr = locStr.substring(0, commaPos);
          String lonStr = locStr.substring(commaPos + 1);
          
          double lat = latStr.toInt() / 10e4;
          double lon = lonStr.toInt() / 10e4;

          //Serial.println(lat);
          //Serial.println(lon);
          double distance = haverSine(lat, lon, TARGET_LOCATION.lat, TARGET_LOCATION.lon) * 10e2;
          long result = (long) distance;
          replyOk(String(result) + "m");
          if (result < 30) {
            foundLocation = true;
          } else {
            foundLocation = false;
          }
        }
      }
      return true;
    }
    return false;
  }
};

LocationCommandHandler locHandler;
WebServerCommandHandler webHandler;
WifiCommandHandler wifiHandler;

CommandHandler* handlers[] = {
  &locHandler,
  &webHandler,
  &wifiHandler
};

void setup() {
  Serial.begin(115200);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
  establishContact();  // send a byte to establish contact until receiver responds
}

void loop() {
  // if we get a valid byte, read analog ins:
  if (Serial.available() > 0) {
    bool handlerFound = false;
    String data = Serial.readStringUntil('\n');
    for (CommandHandler* h : handlers) {
      if (h->handleData(data)) {
        handlerFound = true;
        break;
      }
    }
    if (!handlerFound) {
      Serial.println("-ERROR");
    }
  }
  if (webServerEnabled) {
    webServe();
  }
  if (foundLocation) {
    Serial.println("-INFO=the secret is : " + String(TARGET_LOCATION.secret));
    delay(2000);
  }
}

void establishContact() {
  displayRotatingText("J'attends vos ordres mon capitaine !", 12);
  while (Serial.available() <= 0) {
    Serial.println("-INFO=waiting for command");
    delay(1000);
  }
  displayRotatingText("Passons aux choses sérieuses...", 12);
}

void displayRotatingText(String text, int fontSize) {
  
}

void webServe() {
  // listen for incoming clients
  WiFiClient client = server.available();
  if (client) {
    //Serial.println("new client");
    // an http request ends with a blank line
    boolean currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        //Serial.write(c);
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
          client.print(WEB_PAGE);
          client.println("</html>");
          break;
        }
        if (c == '\n') {
          // you're starting a new line
          currentLineIsBlank = true;
        } else if (c != '\r') {
          // you've gotten a character on the current line
          currentLineIsBlank = false;
        }
      }
    }
    // give the web browser time to receive the data
    delay(1);

    // close the connection:
    client.stop();
    //Serial.println("client disonnected");
  }
}

/*
<!-- #######  YAY, YOU'RE CLEVER! #########-->
<h1 style="color: #5e9ca0;">Welcome to my <span style="color: #2b2301;">documentation</span> !</h1>
<h2 style="color: #2e6c80;">How to find my protocol:</h2>
<ol style="list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;">
<li style="clear: both;"><img style="float: left;" src="https://d30y9cdsu7xlg0.cloudfront.net/png/77784-200.png" alt="interactive connection" width="45" /> Start with 20 Squats</li>
<li style="clear: both;"><img style="float: left;" src="http://www.clipartkid.com/images/125/push-up-20clipart-clipart-panda-free-clipart-images-kYOE0A-clipart.png" alt="html cleaner" width="45" /> Carry on with 25 Push-ups</li>
<li style="clear: both;"><img style="float: left;" src="http://uoah.org/workout/images/Burpees.gif" alt="Word to html" width="45" /> Now do 15 Burpees</li>
<li style="clear: both;"><img style="float: left;" src="https://cdn1.iconfinder.com/data/icons/athlete-sport-exercise/301/athlete_pictogram_sport_sit_up_workout-512.png" alt="replace text" width="45" /> 20 sit-ups to finish !</li>
</ol>
<!-- ####### http://TBD #########-->
<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>

<p><strong>&nbsp;</strong></p>
<strong>Enjoy little Wilders!</strong></p>
<p><strong>&nbsp;</strong></p>
 */

