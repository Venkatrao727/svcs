#! /bash/sh

headerFile=$2
dataFile=$3

#converting into upper case.
method=`echo $1 | tr "[:lower:]" "[:upper:]"`

#url
url="localhost:8080/slocamo_svcs/"$4;

# Printing data
echo "\nProcessing request $method, with headers in $headerFile, data in $dataFile ...\n"

echo "Header contents :";
echo "=================";

#Reading each line from "header file" to use in curl command
while read line; do
  echo $line;
  curlHeader="$curlHeader -H $line";
done < $headerFile

echo "\n";
echo "Parameters :";
echo "============";
while read line; do
  echo $line;
  if [ ! -z "$seperator" ]; then
    curlData="$seperator$curlData"; 
  fi
  curlData="$line$curlData";
  seperator="&";
done < $dataFile

echo "\n";

#checking if there are params
if [ ! -z "$curlData" ]; then
#If request is GET adding the params to url itself and clearing data part.
  if [ $method = "GET" ]; then
    url="$url?$curlData";
    curlData="";  
  else
    curlData="-d  $curlData"; 
  fi	
fi

echo "curl -v $curlHeader -X $method $curlData $url";
response=`curl -v $curlHeader -X $method $curlData $url`;

echo "\n\nresponse: "
echo "============"
echo "$response";
