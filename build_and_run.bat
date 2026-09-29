@echo off
setlocal
if not exist out mkdir out
for /r src %%f in (*.java) do call set FILES=%%FILES%% "%%f"
javac --release 17 -d out %FILES%
if errorlevel 1 (
  echo Compilation failed.
  exit /b 1
)
echo Compilation successful.
java -cp out planner.console.PlannerApp
