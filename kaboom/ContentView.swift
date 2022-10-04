//
//  ContentView.swift
//  kaboom
//
//  Created by Atai Moldokulov on 14/9/22.
//

import SwiftUI

enum BombState {
    case exploded, defused, beingDefused
}
struct ContentView: View {
    @State var btn1Presses: Int = Int.random(in: 0...5)
    @State var btn1Pressed: Int = 0
    @State var btn2Presses: Int = Int.random(in: 0...5)
    @State var btn2Pressed: Int = 0
    @State var btn3Presses: Int = Int.random(in: 0...5)
    @State var btn3Pressed: Int = 0
    @State var someWords = ""
    @State var redWireConnected = true
    @State var redWireDisconnect = Bool.random()
    @State var greenWireConnected = true
    @State var greenWireDisconnect = Bool.random()
    @State var blueWireConnected = true
    @State var blueWireDisconnect = Bool.random()
    @State var password = ""
    @State var bombState = BombState.beingDefused
    
    
    func restart(){
        btn1Presses = Int.random(in: 0...5)
        btn1Pressed = 0
        btn2Presses = Int.random(in: 0...5)
        btn2Pressed = 0
        btn1Presses = Int.random(in: 0...5)
        btn1Pressed = 0
        bombState = .beingDefused
        redWireConnected = true
        redWireDisconnect = Bool.random()
        greenWireConnected = true
        greenWireDisconnect = Bool.random()
        blueWireConnected = true
        blueWireDisconnect = Bool.random()
    }
    
    func generateInstructions() -> String {
        var instructions: [String] = []
        
        btn1Presses = Int.random(in: 0...5)
        btn1Pressed = 0
        
        if(btn1Presses > 0){
            instructions.append("Press Button '1' \(btn1Presses) \(btn1Presses == 1 ? "time" : "times")")
        }
        btn2Presses = Int.random(in: 0...5)
        btn2Pressed = 0
        
        if(btn2Presses > 0){
            instructions.append("Press Button '2' \(btn2Presses) \(btn2Presses == 1 ? "time" : "times")")
        }
        btn1Presses = Int.random(in: 0...5)
        btn1Pressed = 0
        
        if(btn1Presses > 0){
            instructions.append("Press Button '3' \(btn3Presses) \(btn3Presses == 1 ? "time" : "times")")
        }
// wires functionality
        
        redWireConnected = true
        redWireDisconnect = Bool.random()
        greenWireConnected = true
        greenWireDisconnect = Bool.random()
        blueWireConnected = true
        blueWireDisconnect = Bool.random()
        
        if(redWireDisconnect == true){
            instructions.append("Disconnect Red wire")
        }
        if(greenWireDisconnect == true){
            instructions.append("Disconnect Green wire")
        }
        if(blueWireDisconnect == true){
            instructions.append("Disconnect Blue wire")
        }
        
        return instructions.joined(separator: ".")
    }
    
    func defuse(){
        if (btn1Presses == btn1Pressed &&
            btn2Presses == btn2Pressed &&
            btn3Presses == btn3Pressed &&
            redWireConnected != redWireDisconnect &&
            greenWireConnected != greenWireDisconnect &&
            blueWireConnected != blueWireDisconnect

        ){
            bombState = .defused
        }else{
            bombState = .exploded
        }
    }
    
    var body: some View {
        VStack {
            Form {
                switch bombState {
                case .exploded:
                    Image("exploded")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                case .defused:
                    Image("defused")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                case .beingDefused:
                    Text("Disarm the bomb by following the instructions below.")
                        .padding()
                    Text(generateInstructions())
                    HStack{
                        Spacer()
                        Button("1"){
                            btn1Pressed += 1
                        }.buttonStyle(.borderedProminent)
                        Button("2"){
                            btn2Pressed += 1
                        }.buttonStyle(.borderedProminent)
                        Button("3"){
                            btn3Pressed += 1
                        }.buttonStyle(.borderedProminent)
                        Spacer()
                    }
                    TextField("code phrase", text: $someWords)
                    Toggle(isOn: $redWireConnected){
                        Text("Red wire is connected").foregroundColor(.red)
                    }
                    Toggle(isOn: $greenWireConnected){
                        Text("Green wire is connected").foregroundColor(.green)
                    }
                    Toggle(isOn: $blueWireConnected){
                        Text("Blue wire is connected").foregroundColor(.blue)
                    }
                    SecureField("Password", text: $password)
                }
                
                HStack(spacing: 40){
                    Spacer()
                    if (bombState == .beingDefused){
                        Button("Defuse", role: .destructive){
                            defuse()
                        }.buttonStyle(.borderedProminent)
                    }

                    Button("Restart"){
                        restart()
                    }.buttonStyle(.borderedProminent)
                    Spacer()
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .previewInterfaceOrientation(.portrait)
    }
}
