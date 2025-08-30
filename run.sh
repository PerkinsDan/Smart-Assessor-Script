#!/usr/bin/env bash

set -e

read -p "Fetch ICAL? (Y/y) " fetch

if [ "$fetch" == "Y" ] || [ "$fetch" == "y" ]; then
	# Python Script
	BASE_DIR="csv"
	ENV_DIR="$BASE_DIR/venv"
	PYTHON_SCRIPT="$BASE_DIR/csv_converter.py"
	REQUIREMENTS="$BASE_DIR/requirements.txt"

	if [ ! -d "$ENV_DIR" ]; then
	  echo "Creating virtual environment..."
	  python3 -m venv $ENV_DIR
	else
	  echo "Virtual environment already exists."
	fi

	source $ENV_DIR/bin/activate

	echo "Upgrading pip..."
	pip install --upgrade pip

	if [ -f $REQUIREMENTS ]; then
	  echo "Installing dependencies from requirements.txt..."
	  pip install -r $REQUIREMENTS 
	else
	  echo "No requirements.txt found, skipping dependency install."
	fi

	echo "Running $PYTHON_SCRIPT..."
	python $PYTHON_SCRIPT

	deactivate
fi

read -p "Fill in CSV and press ENTER to continue..."

# Java OTJ Creator
BASE_DIR="./OTJWriter"
JAR="$BASE_DIR/target/OTJWriter-1.0-SNAPSHOT.jar"
java -jar $JAR
