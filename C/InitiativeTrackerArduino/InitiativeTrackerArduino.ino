// First multiplexer for the green lights
int gm1 = 0;
int gm2 = 0;
int gm3 = 0;
int gm4 = 0;

// Second multiplexer for the orange lights
int om1 = 0;
int om2 = 0;
int om3 = 0;
int om4 = 0;

int bin[] = {0000, 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111};

int order[];

int currentIndex = 0;

void setup() {
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);

  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  
  Serial.begin(9600);
}

void loop() {
  // Read either a number, or a word (start or end)
  value = this.getSerialString();
  if (value.length != 0) {
    if (value == "start") {
      order = [];
      reading = true;
      currentIndex = 0;
    }
    else if (value == "end") {
      reading = false;
    }
    else {
      order[order.length] = (int)value;
    }
  }
    

  if (!reading) {
    if (button.ispressed) {
      this.lightNext();
      delay(1000);
    }
  }
}

void lightNext() {
  currentIndex++;

  // http://thecodeinn.blogspot.com/2013/10/arduino-and-multiplexing.html
  // TODO check end array

  int green = bin[order[currentIndex] - 1];
  int orange = bin[order[currentIndex]];

  gm1 = bitRead(green, 0);
  gm2 = bitRead(green, 1);
  gm3 = bitRead(green, 2);
  gm4 = bitRead(green, 3);

  om1 = bitRead(orange, 0);
  om2 = bitRead(orange, 1);
  om3 = bitRead(orange, 2);
  om4 = bitRead(orange, 3);

  digitalWrite(2, gm1);
  digitalWrite(3, gm2);
  digitalWrite(4, gm3);
  digitalWrite(5, gm4);

  digitalWrite(6, om1);
  digitalWrite(7, om2);
  digitalWrite(8, om3);
  digitalWrite(9, om4);
}

String getSerialString() {
  String value;
  while (Serial.available() > 0) {
    value = value + Serial.read();
  }
  return value;
}

