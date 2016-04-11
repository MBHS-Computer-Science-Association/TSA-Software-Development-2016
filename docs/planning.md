Chronological
==
**Monday, April 4, 2016**
	- discuss prospects of project overhaul
	- code base redesign
	- architecture reworking
	- better separation between api and demonstration

**Tuesday, April 5, 2016**
    - Node object
      - inputs
      - outputs
    - synchronized cycles
    - independently managed node functions
    - binary decision for correction
      - reduced ability to determine effectiveness and fine tune
      - mitigated by multiple trials, but requires more computation

**Wednesday, April 6, 2016**
    - discussing robot tank demonstration
    - could implement neural network with memory

**Thursday, April 7, 2016**
	- finally implemented code base redesign on new Git branch
	- created package divisions between demo and software package
	- demonstration is in completely different source folder
	- package-info.java files are present
### Software Architecture
* synchronized calculation cycle
* asynchronous transfer function
* weakly referenced hidden nodes
  * continues layer of abstraction
  * improves garbage collection
  * may need iteration
* independently managed node functions
* implement memory function
  * elevated design considerations
* Node class
  * list of inputs (nodes?)
  * list of outputs (nodes?)
  * functional interface to store transfer function

### Demonstration Ideas
- mathematical formula recognition
- numeral recognition from pixels
- intelligent tanks
- environmental survival

### Research

- genetic algorithms vs neural networks
  - random changes vs calculated changes
- mathematical capability of neural networks
- transfer functional
- backward propagation
