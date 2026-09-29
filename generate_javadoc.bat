@echo off
setlocal
if exist docs\javadoc rmdir /s /q docs\javadoc
mkdir docs\javadoc
javadoc -d docs\javadoc -sourcepath src -subpackages planner
if errorlevel 1 (
  echo Javadoc generation failed.
  exit /b 1
)
echo Javadoc generated successfully.
echo Open docs\javadoc\index.html in your browser.
