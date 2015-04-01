# Authorship Attribution
AcqDeCo Project on authorship attribution.

In this project, the aim is to build a classifier able to efficiently attribute a new text to his/her
author, relying on textual characteristics (words, stems, ngrams of words or characters, punctuation
maks, etc.). At least two different representations of the textual data have to be compared.

## Collaborators
- BOUVET Julien
- BHATNAGAR Prateek
- GAUGRY Thierry
- PREVOSTO Gabtiel
- RASATA-MANANTENA Liantsoa

## List of tasks
- Stemming (for 2d representation) (*Liantsoa*)
- Generate file list (with authors) (*Julien*)
- Data extraction for first representation (*Thierry*)
  * Length of sentences
  * Length of paragraphs
  * Frequency of punctuation
- Data extraction for second representation (*Liantsoa*) (*Prateek*)
  * Most frequent words used (after stemming)
  * Frequency of articles, adjectives...
- First classifier (*Thierry*) (*Julien*)
- Second calssifier (*Gabriel*) (*Liantsoa*) (*Prateek*)
- Statistics about results (*Gabriel*)


- I will reorganize files later so we all have the same eclipse project. (*Thierry*)

## Plan of the report
This plan is temporary, if you want to modify it mail the group first

- Introduction
- The two models chosen (no technical details here)
- Stemming : why and how ?
- The first classifier
  * Data acquisition
  * Data processing
- The second classifier (I will write this part (Liantsoa))
  * Data acquisition
  * Data processing
- Comparison of the two classifiers
  * The result evaluation methodology
  * The final verdict
- Conclusion
