Source files of the prototypical tool BPMN+TD to model selected aspects of Technical Debt with BPMN introduced in the paper:\
Dong, Q. H., and Vogel-Heuser, B. (2021). Modelling technical compromises in electronics manufacturing with BPMN+TD – an industrial use case. IFAC-PapersOnLine: 17th IFAC Symposium on Information Control Problems in Manufacturing (INCOM), 54(1), pp. 912-917. Springer. https://doi.org/10.1016/j.ifacol.2021.08.108
Status: Archive (code is provided as-is, no updates expected)


# Prototypical tool BPMN+TD

The code is based on the project BPMN2-Modeler/DeveloperTutorial provided by rsoika (https://github.com/rsoika/bpmn2-tutorial/) extending Eclipse BPMN2 Modeler Plugin.\ 
BPMN+TD implements two custom elements, namely TechnicalDebtTask inheriting from Task element and TechnicalDebtItem inheriting from TextAnnotation element.\
More information about Eclipse BPMN2 Modeler Plugin can be found on https://www.eclipse.org/bpmn2-modeler/


# Environment

BPMN+TD environment is similar to the environment of BPMN2-Modeler/DeveloperTutorial.
- Install Eclipse using Oxygen release.
- Install BPMN2 Modeler plugin and its dependencies into Eclipse (http://download.eclipse.org/bpmn2-modeler/updates/).
- Build the code and run.

The presented model in the paper is provided in the Project2 folder.


# License

Eclipse Public License 2.0


# Contact

Quang Huan Dong - huan.dong@tum.de\
Technical University of Munich - Institute of Automation and Information Systems https://www.mw.tum.de/en/ais/homepage/
