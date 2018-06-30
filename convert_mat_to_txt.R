library(R.matlab)
getwd()
#colocar o nome do arquivo sem a extensao .mat
name = 'v100sm'
name = 't106sm'

filename = paste(name,'.mat', sep = '')
m = readMat(filename)
filename_output = paste('out_',name, '.txt', sep = "")
write(m$val, filename_output)
