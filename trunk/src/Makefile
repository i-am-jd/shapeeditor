# Executables
JAVAC = javac
JAVA = java
APPLETVIEWER = appletviewer
A2PS = a2ps
GHOSTVIEW = gv
DOCP = javadoc
ARCH = zip
PS2PDF = ps2pdf -sPAPERSIZE=a4
DATE = $(shell date +%Y-%m-%d)
# Options de compilation
#CFLAGS = -verbose
CFLAGS =

PROJECT=Main
# nom du fichier d'impression
OUTPUT = $(PROJECT)
# nom du répertoire ou se situera la documentation
DOC = doc
# lien vers la doc en ligne du JDK
WEBLINK = "http://java.sun.com/javase/6/docs/api/"
# lien vers la doc locale du JDK
LOCALLINK = "file:///C:/Program%20Files/Java/jdk1.6.0_03/docs/api/"
# nom de l'archive
ARCHIVE = $(PROJECT)-$(DATE)
# format de l'archive pour la sauvegarde
ARCHFMT = zip
# Répertoire source
SRC = src
# Répertoire bin
BIN = bin
# Répertoire Listings
LISTDIR = listings
# Répertoire Archives
ARCHDIR = archives
# noms des fichiers sources
SOURCES = $(SRC)/Main.java \
$(SRC)/IHM/DrawPanel.java \
$(SRC)/IHM/InfoBar.java \
$(SRC)/IHM/JLabeledComboBox.java \
$(SRC)/IHM/JLabeledSlider.java \
$(SRC)/IHM/JLabeledTextField.java \
$(SRC)/IHM/JNumTextField.java \
$(SRC)/IHM/MenuBar.java \
$(SRC)/IHM/OptionPanel.java \
$(SRC)/IHM/SelectionContextMenu.java \
$(SRC)/IHM/ToolBar.java \
$(SRC)/IHM/TreePanel.java \
$(SRC)/IHM/UserMode.java \
$(SRC)/IHM/Window.java \
$(SRC)/Listeners/CopyActionListener.java \
$(SRC)/Listeners/DeleteActionListener.java \
$(SRC)/Gestionnaire/GestionnaireColors.java \
$(SRC)/Gestionnaire/GestionnaireToolBar.java \
$(SRC)/Gestionnaire/GestionnaireWidth.java \
$(SRC)/Draw/BinaryOperation.java \
$(SRC)/Draw/Circle.java \
$(SRC)/Draw/Ellipse.java \
$(SRC)/Draw/Exclusion.java \
$(SRC)/Draw/Geometry.java \
$(SRC)/Draw/Group.java \
$(SRC)/Draw/Inclusion.java \
$(SRC)/Draw/Interpolation.java \
$(SRC)/Draw/Intersection.java \
$(SRC)/Draw/IrregularPolygon.java \
$(SRC)/Draw/IsoscelesTriangle.java \
$(SRC)/Draw/Rectangle.java \
$(SRC)/Draw/RightAngledTriangle.java \
$(SRC)/Draw/Rotation.java \
$(SRC)/Draw/Scale.java \
$(SRC)/Draw/SceneGraph.java \
$(SRC)/Draw/SceneShape.java \
$(SRC)/Draw/Shear.java \
$(SRC)/Draw/Square.java \
$(SRC)/Draw/Star.java \
$(SRC)/Draw/Substraction.java \
$(SRC)/Draw/Transformation.java \
$(SRC)/Draw/Translation.java \
$(SRC)/Draw/UnaryOperation.java \
$(SRC)/Draw/Union.java \
$(SRC)/Draw/View.java \
$(SRC)/DragNDrop_JTree/MyBranchNode.java \
$(SRC)/DragNDrop_JTree/MyLeafNode.java \
$(SRC)/DragNDrop_JTree/MyNode.java \
$(SRC)/DragNDrop_JTree/MyTree.java \
$(SRC)/DragNDrop_JTree/TransferableTreeNode.java \
$(SRC)/DragNDrop_JTree/TreeDragSource.java \
$(SRC)/DragNDrop_JTree/TreeDropTarget.java

FILES = Makefile $(SOURCES) $(SRC)/images/* \
$(SRC)/*/package.html $(LISTDIR)/*.pdf

.PHONY : doc ps

# Les targets de compilation
# pour générer l'application
all : $(BIN)/Main.class

$(BIN)/Main.class : $(SRC)/Main.java

#règle de compilation générique
$(BIN)/%.class : $(SRC)/%.java
	$(JAVAC) -sourcepath $(SRC) -d $(BIN) $(CFLAGS) $<

# Edition des sources $(EDITOR) doit être une variable d'environnement
edit :
	$(EDITOR) $(SOURCES) Makefile &

# nettoyer le répertoire
clean :
	rm -rf $(BIN)/* $(LISTDIR)/$(OUTPUT).ps $(LISTDIR)/$(OUTPUT).pdf *~ $(DOC)/*

realclean : clean
	rm -f $(ARCHDIR)/*.$(ARCHFMT)

# générer le listing
ps :
	$(A2PS) -2 --file-align=fill --line-numbers=1 --font-size=10 \
	--chars-per-line=100 --tabsize=4 --pretty-print \
	--highlight-level=heavy --prologue="gray" \
	-o$(LISTDIR)/$(OUTPUT).ps Makefile $(SOURCES)

pdf : ps
	$(PS2PDF) $(LISTDIR)/$(OUTPUT).ps $(LISTDIR)/$(OUTPUT).pdf

# voir le listing
preview : ps
	$(GHOSTVIEW) $(LISTDIR)/$(OUTPUT); rm -f $(LISTDIR)/$(OUTPUT) $(LISTDIR)/$(OUTPUT)~

# générer la doc avec javadoc
doc : $(SOURCES)
	$(DOCP) -private -d $(DOC) -author -link $(WEBLINK) $(SOURCES)

# générer une archive de sauvegarde
archive : pdf
	mkdir -p $(ARCHDIR);
	$(ARCH) $(ARCHDIR)/$(ARCHIVE).$(ARCHFMT) $(FILES) $(BIN);

# copie des fichiers nécessaires à l'exécution dans bin
copyFiles : 
	mkdir -p $(BIN)/images;
	cp $(SRC)/images/* $(BIN)/images/;

# exécution du programme de test
run : all copyFiles
	cd $(BIN)/; $(JAVA) Main;
