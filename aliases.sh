#---------------------------------------------------------------------------------------------------
#-- File:       aliases.sh
#--
#-- Encoding:   UTF-8 (Without BOM)
#--
#-- Project:    (NONE)
#--
#-- Module:     (NONE)
#--
#-- Type:       BASH script
#--
#-- Date:       2014.08.02
#--
#-- Designer:   Norbert Varga Attila (nova)
#--
#-- Description:
#--     Shell alias file.
#---------------------------------------------------------------------------------------------------
#-- Date        | Name      | Brief description
#---------------------------------------------------------------------------------------------------
#-- 2014.08.02  | nova      | Initial
#---------------------------------------------------------------------------------------------------

#---------------------------------------------------------------------------------------------------
#-- General aliases
#---------------------------------------------------------------------------------------------------
alias a=alias                                               # Alias Alias :D

a n='notepad++'                                             # Open editor
a ea='n ${ALIAS_PATH} &'                                    # Edit aliases
a sa='source ${ALIAS_PATH}'                                 # Source aliases
a p='pwd'                                                   # Print working directory
a ll='ls -A -l --color=always -X'                           # List all files a directories
a ..='cd ..'                                                # Change to parent directory

#---------------------------------------------------------------------------------------------------
#-- Project aliases
#---------------------------------------------------------------------------------------------------
# TODO: ...

#---------------------------------------------------------------------------------------------------
#-- Path aliases
#---------------------------------------------------------------------------------------------------
a cdr='cd "${REPO_PATH}"'                                   # Go to repository
a cdm='cdr; cd modules'                                     # Go to modules
a cdc='cdr; cd common/hdl'                                  # Go to common (hdl)

#---------------------------------------------------------------------------------------------------
#-- HDL aliases
#---------------------------------------------------------------------------------------------------
#a ee='n ` find -name *_e.vhd` &'                            # Edit hdl entity
#a ertl='n `find -name *_rtl.vhd` &'                         # Edit hdl rtl architecture
#a exil='n `find -name *_xil.vhd` &'                         # Edit hdl xil architecture
#a epkg='n `find -name *_pkg.vhd` &'                         # Edit hdl package
# a ebdy='n `find -name *_bdy.vhd` &'                         # Edit hdl package body
# a etb='n `find -name *_tb.vhd` &'                           # Edit hdl test-bench
# a efl='n `find -name hdl_file_list` &'                      # Edit hdl file list
# a esml='n `find -name sub_module_list` &'                   # Edit hdl file list

# a gfl='genHDLFileList.sh'                                   # Generate hdl file list

# a cfl='rm -f `find -name hdl_file_list` &'                  # Clean hdl file list

#---------------------------------------------------------------------------------------------------
#-- Global aliases
#---------------------------------------------------------------------------------------------------
# a ca='cfl'                                                  # Clean all

#---------------------------------------------------------------------------------------------------
#-- Xilinx aliases
#---------------------------------------------------------------------------------------------------
# a xrun='${XILINX_ISE_PATH}'                                 # Open Xilinx ISE

#---------------------------------------------------------------------------------------------------
#-- Git aliases
#---------------------------------------------------------------------------------------------------
a sorg='git remote add innovator_repo_org "${REPO_URL}"'    # Set repository origin
a usorg='git remote rm innovator_repo_org'                  # Unset repository origin
a cln='git clone "${REPO_URL}"'                             # Clone repository
a init='git init; sorg'                                     # Initialize repository
a stat='git status'                                         # Status of repository
a add='git add'                                             # Add single changes to staging area
a adda='add --all'                                          # Add all changes to staging area
a cmt='git commit --allow-empty-message --no-edit'          # Commit single modifications
a cmta='cmt --all'                                          # Commit all modifications
a push='git push -u innovator_repo_org master'              # Push to remote repository
a pull='git pull innovator_repo_org master'                 # Pull changes from remote repository
a cfg='git config'                                          # Git configure
a diff='git diff --color-words HEAD^ HEAD'                  # Difference between current and previous
a co='git checkout -- '                                     # Get the last committed version
a rst='git reset '                                          # Empty staging area

a ggg='stat; adda; cmta; push; stat'                        # Instantaneously push all modifications


#---------------------------------------------------------------------------------------------------
#-- Executed aliases
#---------------------------------------------------------------------------------------------------
cdr;
