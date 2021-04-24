# Adapted from https://unix.stackexchange.com/questions/21930/last-failed-command-in-bash
set -eEuo pipefail

cur_command=
first_err_command=
first_err_lineno=
# This trap is executed in exactly the same conditions in which the `set -e` results in an exit.
trap 'cur_command=$BASH_COMMAND;
      if [[ -z "$first_err_command" ]]; then
          first_err_command=$cur_command;
          first_err_lineno=$LINENO;
      fi' ERR
trap 'if [[ ! -z "$first_err_command" ]]; then
          echo "ERROR: Aborting at line: $first_err_lineno on command: $first_err_command";
      fi' EXIT


