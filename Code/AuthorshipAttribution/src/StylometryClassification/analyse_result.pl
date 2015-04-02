#!/usr/bin/perl -s
my ($ligne);
my ($ligneAuthor);
my ($string);
my @authors = ();
my ($tailleMax);

my @matrice = ();


if (open FSRCA, "<Authors.txt")
{
	@lignesAuthor=<FSRCA>;
	$index = 0;
	foreach $ligneAuthor(@lignesAuthor){
		if($ligneAuthor =~ m/([a-zA-z]+)/){
			$authors[$index] = $1;
			$index++;
		}
	}

}




if (open FSRC, "<Result.txt")
{  
	$tailleMax = $o;
	for($il = 0; $il<$tailleMax; $il++){
		for($ic = 0; $ic<$tailleMax; $ic++){
			$matrice[$il][$ic]=0;
		}
	}
  if(open FDST, ">ConfusionMatrix.csv")
  {
		@lignes=<FSRC>;
		$true_author=0;
		$other_author=0;
		$flag = 0;
   
		foreach $ligne(@lignes)
		{
			if ($ligne=~ m/C50test\/([a-zA-Z]+)\//)
			{
				for($val=0; $val<$tailleMax; $val++){
					if($1 =~ m/$authors[$val]/){
						$true_author = $val;
						
					}
				}
				$flag = 1;
			}else{
				if (($ligne=~ m/1.\s([a-zA-Z]+)\s([1-9][0-9]*)/) && ($flag == 1)){
					
					$string = $1;
					if(($authors[$true_author] =~ /$string/)){
						$matrice[$true_author][$true_author] = $matrice[$true_author][$true_author]+1;
						$flag = 0;
					}else{
					
						for($other_val=0; $other_val<$#authors; $other_val++){

							if($string =~ $authors[$other_val]){
								$other_author = $other_val;
							}
						}
						if($authors[$other_author] =~ $string){
							$matrice[$other_author][$true_author] = $matrice[$other_author][$true_author] +1;
							
						}
						$flag = 0;
					
					}
				}
			}
			
		}
		
		for($il = 0; $il<$tailleMax; $il++){
			for($ic = 0; $ic<$tailleMax; $ic++){
				print FDST "$matrice[$il][$ic];"; 
			}
			print FDST "\n"; 
		}
	
	
	}else{
		print "Erreur : ne peut pas ecrire dans le fichier de destination"
	}

}else{
	print "Erreur : ne peut pas ouvrir le fichier source "
}








        

