# Neural-Network

Combines both Regular Network and Encoder Parts

Network Constrctor
Network (Training Set, Int array of neurons per layer)

2 important things:
1) There always has to be 1 extra layer with 1 neuron - it is used for processing inputs and learning
2) There are no checks if the structure is valid for a task, network will be initialized and learn gibberish if it was invalid

Train method:
Network.train(boolean instruction to make an encoder)
From the boolean value is decided what are the Wanted Results during the training process
