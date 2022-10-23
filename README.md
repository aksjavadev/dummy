# dummy

This is simple maven project and can be imported from any ide as simple maven project

You should be ready with folder structure as follows --

Files to be read should be kept under -- C:\read-write-problem\test-github\read <br>
Files written serially will be generated under -- C:\read-write-problem\test-github\serial-write <br>
Files written parallely will be generated under --C:\read-write-problem\test-github\serial-write <br>

Files to be read should be named as file-1.txt, file-2.txt, file-3.txt and so on in incrememntal order

Execution will start from Client.java
When you run Client.java, it will ask for number of files to be read ( input must be an integer )

Once number of files are provided. Client.java will first run read/write in serial manner and output the total time taken for serial execution
After that it will run the read/write in parallel manner and output the total time taken for parallel execution.



Sample output when run with 4 files
=====================================

Please input number of files

4

FIle names read will be as follows file-1.txt, file-2.txt...so on

Total time taken for serial write -- 4940 ms

Total time taken for parallel write -- 991 ms
