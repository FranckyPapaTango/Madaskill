import React, { useState } from 'react';
import { Button, Alert } from 'reactstrap';
import axios from 'axios';

const BackupComponent = () => {
  const [backupStatus, setBackupStatus] = useState('');
  const [updateStatus, setUpdateStatus] = useState('');
  const [error, setError] = useState(null);

  const handleCreateBackup = () => {
    axios
      .post('/api/create-backup')
      .then(response => {
        setBackupStatus('Backup created successfully');
        setError(null);
      })
      .catch(error => {
        setBackupStatus('Failed to create backup');
        setError(error.response ? error.response.data : error.message);
      });
  };

  const handleUpdateDatabase = () => {
    axios
      .post('/api/update-database')
      .then(response => {
        setUpdateStatus('Database updated successfully');
        setError(null);
      })
      .catch(error => {
        setUpdateStatus('Failed to update database');
        setError(error.response ? error.response.data : error.message);
      });
  };

  return (
    <div>
      <Button color="primary" onClick={handleCreateBackup}>
        Create Backup
      </Button>
      {backupStatus && <Alert color={backupStatus.includes('successfully') ? 'success' : 'danger'}>{backupStatus}</Alert>}

      <Button color="secondary" onClick={handleUpdateDatabase}>
        Update Database From Script
      </Button>
      {updateStatus && <Alert color={updateStatus.includes('successfully') ? 'success' : 'danger'}>{updateStatus}</Alert>}

      {error && <Alert color="danger">Error: {error.title ? `${error.title} - ${error.message}` : error.message}</Alert>}
    </div>
  );
};

export default BackupComponent;
