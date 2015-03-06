*****************************************
Running Maven from Console
*****************************************
1. Assuming Maven is already installed in your computer.
2. Open a console and navigate to the directory where the project is un-zipped (e.g., /data/Examples/camel/camel-orch-example)
3. To run Maven, execute the following command form the console:  mvn clean install
4. This should build your project.

*****************************************
Import Project in Eclipse
*****************************************
1. Navigate to File > Import from the menu.
2. Select Maven > Existing Maven Projects and click Next.
3. Brose to select the location of this project.
4. Click Finish.

*****************************************
Modify Endpoints
*****************************************
1. From Eclipse IDE, navigate to camel-orch-example project.
2. Navigate to src/main/resources/META-INF/spring folder.
3. Double click came-context.xml file to open in the editor.
4. Change the following endpoints to point to right directories in your computer:
deadLetterUri="file:/data/Examples/camel/camel-example/dead"
<endpoint id="in.queue"uri="file:/data/Examples/camel/camel-orch-example/in?delete=true&amp;consumer.delay=5000" />
<endpoint id="out.queue" uri="file:/data/Examples/camel/camel-orch-example/out" />
<endpoint id="log.queue" uri="file:/data/Examples/camel/camel-orch-example/log" />
5. Save the file

*****************************************
Run the Test Program
*****************************************
1. Find the Java class basaki.TestCamelOrchestration under src/main/java folder.
2. Right click the file in the explorer tree, and Run as > Java application from the contextual menu.
3. This should start the test program.
4. In order to see how the test program works, copy file <install dir>/camel-orch-example/test.txt to input directory specified in end point in.queue (/data/Examples/camel/camel-orch-example/in).
5. You cn notice the processing file in the Eclipse IDE Console view.
6. Log files are created in  log folder while the end result is copied to  file under out directory specified by out.queue endpoint.
7. Every second attempt will fail to process first time. It will be redelivered one time by Camel. The processing will be successful the next time around.
8. Every third attempt will also fail to process. However the redelivery attempt will also fail. The input message will end in the directory specified by deadLetterUri. 