# !/bin/bash
echo "Nama  : Ignatius Henriyanto Primai Renda"
echo "NPM   : 2006525002"
echo "Kelas : C"
echo -e "Assignment UTS SOSI Bagian Scripting\n"

echo "Jumlah Argumen: " $#
if [[ $# -lt 2 ]]
then echo "Argumen harus minimal 2"
else 
for ((i=0; i<=$#;i++))
do
  echo "Argumen ke-${i} adalah ${!i}"
done
echo \

echo -n "Masukkan sebuah kata: "
read word
for (( i=1; i<=$1;i++ ))
do
  for (( j=1;j<=$2;j++ ))
  do
    echo word $i $j
  done
done

echo -n -e "Silahkan pilih di antara opsi berikut: \n1. Print current directory\n2. Print current environment variable\n"
read option
if [[ option -eq 1 ]]
then echo "Anda sedang berada di: $(pwd)"
elif [[ option -eq 2 ]]
then printenv
else
echo "Hanya bisa pilih 1 atau 2"
fi
fi