// PINS WHERE THE RGB MODULE IS CONNECTED
int rPin= 11;
int gPin = 10;
int bPin = 9;

// USED TO RECEIVE THE VALUE OF THE RGB COLOR
int currentValue = 0;
int rgb[] = {0,0,0};

void setup() {
  // SETS THE pinMode OF THE PINS TO OUTPUT
  pinMode(rPin, OUTPUT);
  pinMode(gPin, OUTPUT);
  pinMode(bPin, OUTPUT);
  Serial.begin(9600); // Start serial communication at 9600 bps
}

void loop() {
  // READS THE VALUE OF THE RGB COLOR
  // FROM THE SERIAL PORT ONE BY ONE
  if(Serial.available()){
    int incomingValue = Serial.read();
    
    rgb[currentValue] = incomingValue;

    currentValue++;

    // IF THE CURRENT VALUE IS BEYOND THE
    // LENGTH OF THE ARRAY (3, R G B), IT RESETS
    if(currentValue > 2){
      currentValue = 0;
    }
  }

  // CHANGE THE COLOR OF THE RGB MODULE USING THE FUNCTION
  colorize(rgb[0], rgb[1], rgb[2]);
}

// FUNCTION TO CHANGE THE COLOR OF THE RGB MODULE
void colorize(int rValue, int gValue, int bValue)
 {
  analogWrite(rPin, rValue);
  analogWrite(gPin, gValue);
  analogWrite(bPin, bValue);
}
