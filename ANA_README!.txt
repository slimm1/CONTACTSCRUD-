  -----------------------------------------------------------------------
 |********************** CRUD de Consola by slimm1 **********************|
  -----------------------------------------------------------------------

 ¡¡IMPORTANTE!! --> Para el correcto funcionamiento del programa, debes
 tener en cuenta estos sencillos pasos:
	 
 1.- La clase Main contiene un constructor de la clase "Control".
 En dicho constructor debes introducir los parámetros según los 
 cuales funcionará el programa. Estos parámetros son OBLIGATORIOS
 para que el programa funcione. 
 
 2.- Los parámetros admitidos son:
	 
	- Para el primer parámetro (fileName): el nombre del fichero
	con la extension que se quiere trabajar. Las extensiones 
	soportadas son "file.txt", "file.bin" y "file.xml" 
	por ejemplo: "contacts.txt"

	- Para el segundo parámetro (fileType): el nombre de la extension
	del fichero. Las extensiones contempladas son txt, bin y xml.
	La extensión del primer parámetro DEBE COINCIDIR con la del 
	segundo parámetro. Los tipos admitidos son: "txt", "bin" o "xml"
	
	- Para el tercer parámetro (fileParser): el nombre de la librería 
	que se desea emplear para ESCRIBIR Y LEER SOLO FICHEROS CON 
	EXTENSION XML. Este parámetro debe estar vacío para ejecuciones
	de programa con txt o bin. Los parseadores admitidos son: 
	"xstream", "jaxb", "sax" o "dom" 

 3.- Una vez configurado esto el programa está listo para su ejecución.